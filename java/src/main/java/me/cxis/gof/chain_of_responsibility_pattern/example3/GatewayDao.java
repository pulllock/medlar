package me.cxis.gof.chain_of_responsibility_pattern.example3;

import java.util.HashMap;
import java.util.Map;

public class GatewayDao {

    private static Map<Integer, GatewayDO> gatewayDOMap = new HashMap<>();

    static {
        GatewayEnum[] values = GatewayEnum.values();
        for (GatewayEnum value : values) {
            GatewayDO gatewayDO = value.getGatewayDO();
            gatewayDOMap.put(gatewayDO.getId(), gatewayDO);
        }
    }

    public GatewayDO getGatewayDO(Integer id) {
        return gatewayDOMap.get(id);
    }

    public GatewayDO getFirstGatewayDO() {
        for (Map.Entry<Integer, GatewayDO> gatewayDOEntry : gatewayDOMap.entrySet()) {
            GatewayDO gatewayDO = gatewayDOEntry.getValue();
            if (gatewayDO.getPreId() == null) {
                return gatewayDO;
            }
        }
        return null;
    }
}
