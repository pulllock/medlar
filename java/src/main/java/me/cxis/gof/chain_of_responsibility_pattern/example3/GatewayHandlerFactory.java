package me.cxis.gof.chain_of_responsibility_pattern.example3;

public class GatewayHandlerFactory {

    private static GatewayDao gatewayDao = new GatewayDao();

    public static GatewayHandler getHandler() {
        GatewayDO gatewayDO = gatewayDao.getFirstGatewayDO();
        GatewayHandler firstHandler = instantiationHandler(gatewayDO);

        if (firstHandler == null) {
            return null;
        }

        GatewayDO temp = gatewayDO;
        Integer nextHandlerId;
        GatewayHandler nextHandler = firstHandler;
        while ((nextHandlerId = temp.getNextId()) != null) {
            GatewayDO nextGatewayDO = gatewayDao.getGatewayDO(nextHandlerId);
            GatewayHandler gatewayHandler = instantiationHandler(nextGatewayDO);
            nextHandler.setNextHandler(gatewayHandler);

            nextHandler = gatewayHandler;
            temp = nextGatewayDO;
        }

        return firstHandler;
    }

    private static GatewayHandler instantiationHandler(GatewayDO gatewayDO) {
        String className = gatewayDO.getFullName();

        try {
            Class<?> clazz = Class.forName(className);
            return (GatewayHandler) clazz.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }
}
