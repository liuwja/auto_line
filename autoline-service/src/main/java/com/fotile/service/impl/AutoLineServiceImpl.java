package com.fotile.service.impl;

import com.datasweep.compatibility.client.*;
import com.datasweep.compatibility.manager.ServerImpl;
import com.datasweep.compatibility.ui.Time;
import com.fotile.bean.TransferBean;
import com.fotile.service.IAutoLineService;
import com.rockwell.transactiongrouping.UserTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Vector;

@Service
public class AutoLineServiceImpl implements IAutoLineService {

    @Autowired
    private ServerImpl server;
    @Override
    public String binding(TransferBean bean) {
        try {
            UserTransaction userTransaction = new UserTransaction(server);
            userTransaction.begin();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String consumedPart(TransferBean bean) throws DatasweepException {
        String workCenterName = bean.getWorkCenterName();

        WorkCenter workCenter = (WorkCenter) server.getWorkCenterManager().getObject(workCenterName);
        if (workCenter==null){
            throw new RuntimeException("未找到工作中心："+workCenterName);
        }

        List<ProductionLine> productionLines = workCenter.getProductionLines();
        if (productionLines.size()<1){
            throw new RuntimeException("工作中心："+workCenterName+"未配置产线");
        }
        ProductionLine productionLine = productionLines.get(0);

        Unit unit = (Unit) server.getUnitManager().getObject(bean.getSerialNumber());
        if (unit==null){        //如果查找步到unit，则先进行投产操作
            ATRowFilter atRowFilter = new ATRowFilter("PrintRecord",server);
            atRowFilter.forColumnNameEqualTo("barcode",bean.getSerialNumber());
            atRowFilter.forColumnNameEqualTo("record_type","打印");
            atRowFilter.setMaxRows(1);
            List<ATRow> printRecord = atRowFilter.exec();
            if (printRecord.size()<1){
                throw new RuntimeException("未找到主机条码："+bean.getSerialNumber()+"的打印记录");
            }
            //获取工单号
            String orderNumber = (String) printRecord.get(0).getValue("order_number");
            Order order = (Order) server.getOrderManager().getObject(orderNumber);
            if (order==null){
                throw new RuntimeException("未找到工单号："+orderNumber);
            }
            Vector orderItem = order.getOrderItems(true);
            Time time = server.getUtilityManager().getDBTime();
            int hour = time.getHour();              //当前数据库时间小时
            int minute = time.getMinute();          //当前数据库时间分钟
        }
        return null;
    }
}
