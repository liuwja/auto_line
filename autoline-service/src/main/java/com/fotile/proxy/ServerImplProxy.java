package com.fotile.proxy;

import com.datasweep.compatibility.client.DatasweepException;
import com.datasweep.compatibility.client.Unit;
import com.datasweep.compatibility.client.UnitFilter;
import com.datasweep.compatibility.manager.ServerImpl;

import java.util.List;

/**
 * 该类时对ServerImpl类的二次封装
 */
public class ServerImplProxy{
    private ServerImpl server;
    public ServerImplProxy(ServerImpl server){
        this.server = server;
    }

    /**
     * 根据主机条码获取unit
     * @param serialNumber
     * @return
     */
    public Unit getUnitBySerialNumber(String serialNumber){
        try {
        UnitFilter unitFilter = new UnitFilter(this.server);
        unitFilter.forSerialNumberEqualTo(serialNumber);
        unitFilter.orderByCreationTime(false);      //创建时间倒序排序
        List<Unit> units = unitFilter.exec();
        if (units.size()<1){
            throw new RuntimeException("未找到主机条码："+serialNumber);
        }
        return units.get(0);
        } catch (DatasweepException e) {
            e.printStackTrace();
        }
        return null;
    }
}
