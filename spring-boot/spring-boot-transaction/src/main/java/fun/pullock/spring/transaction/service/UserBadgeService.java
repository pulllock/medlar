package fun.pullock.spring.transaction.service;

import jakarta.annotation.Resource;
import fun.pullock.spring.transaction.manager.UserBadgeLogManager;
import fun.pullock.spring.transaction.manager.UserBadgeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserBadgeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserBadgeService.class);

    @Resource
    private UserBadgeManager userBadgeManager;

    @Resource
    private UserBadgeLogManager userBadgeLogManager;

    public void addSuccessWithoutTransaction() {
        LOGGER.info("addSuccessWithoutTransaction");
        userBadgeManager.addWithoutTransaction();
        userBadgeLogManager.addWithoutTransaction();
    }

    public void addErrorWithoutTransaction() {
        LOGGER.info("addErrorWithoutTransaction");
        userBadgeManager.addWithoutTransaction();
        userBadgeLogManager.addWithoutTransaction();
        throw new RuntimeException("error!");
    }

    /**
     * 使用默认的事务传播类型（REQUIRED），发生异常后会回滚
     * 当前方法有事务，被调用的两个方法无事务
     * debug信息：Creating new transaction with name [...]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * debug信息：Rolling back JDBC transaction on Connection [...]
     */
    @Transactional
    public void addErrorWithTransactionDefault() {
        LOGGER.info("addErrorWithTransactionDefault");
        userBadgeManager.addWithoutTransaction();
        userBadgeLogManager.addWithoutTransaction();
        throw new RuntimeException("error!");
    }

    /**
     * 使用SUPPORTS事务传播类型，发生异常后不会回滚
     * 当前方法有事务，被调用的两个方法无事务
     * debug信息：Should roll back transaction but cannot - no transaction available
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void addErrorWithTransactionPropagationSupports() {
        LOGGER.info("addErrorWithTransactionPropagationSupports");
        userBadgeManager.addWithoutTransaction();
        userBadgeLogManager.addWithoutTransaction();
        throw new RuntimeException("error!");
    }

    /**
     * 使用MANDATORY事务传播类型，直接抛异常，不会执行任务当前方法
     * 当前方法有事务，被调用的两个方法无事务
     * 异常信息：No existing transaction found for transaction marked with propagation 'mandatory'
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void addErrorWithTransactionPropagationMandatory() {
        LOGGER.info("addErrorWithTransactionPropagationMandatory");
        userBadgeManager.addWithoutTransaction();
        userBadgeLogManager.addWithoutTransaction();
        throw new RuntimeException("error!");
    }

    /**
     * 使用REQUIRES_NEW事务传播类型，发生异常后会回滚
     * 当前方法有事务，被调用的两个方法无事务
     * debug信息：Creating new transaction with name [...]: PROPAGATION_REQUIRES_NEW,ISOLATION_DEFAULT
     * debug信息：Rolling back JDBC transaction on Connection [...]
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addErrorWithTransactionPropagationRequiresNew() {
        LOGGER.info("addErrorWithTransactionPropagationRequiresNew");
        userBadgeManager.addWithoutTransaction();
        userBadgeLogManager.addWithoutTransaction();
        throw new RuntimeException("error!");
    }

    /**
     * 使用NOT_SUPPORTED事务传播类型，发生异常后不会回滚
     * 当前方法有事务，被调用的两个方法无事务
     * debug信息：Should roll back transaction but cannot - no transaction available
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addErrorWithTransactionPropagationNotSupported() {
        LOGGER.info("addErrorWithTransactionPropagationNotSupported");
        userBadgeManager.addWithoutTransaction();
        userBadgeLogManager.addWithoutTransaction();
        throw new RuntimeException("error!");
    }

    /**
     * 使用NEVER事务传播类型，发生异常后不会回滚
     * 当前方法有事务，被调用的两个方法无事务
     * debug信息：Should roll back transaction but cannot - no transaction available
     */
    @Transactional(propagation = Propagation.NEVER)
    public void addErrorWithTransactionPropagationNever() {
        LOGGER.info("addErrorWithTransactionPropagationNever");
        userBadgeManager.addWithoutTransaction();
        userBadgeLogManager.addWithoutTransaction();
        throw new RuntimeException("error!");
    }

    /**
     * 使用NESTED事务传播类型，发生异常后不会回滚
     * 当前方法有事务，被调用的两个方法无事务
     * debug信息：Creating new transaction with name [...]: PROPAGATION_NESTED,ISOLATION_DEFAULT
     * debug信息：Rolling back JDBC transaction on Connection [...]
     */
    @Transactional(propagation = Propagation.NESTED)
    public void addErrorWithTransactionPropagationNested() {
        LOGGER.info("addErrorWithTransactionPropagationNested");
        userBadgeManager.addWithoutTransaction();
        userBadgeLogManager.addWithoutTransaction();
        throw new RuntimeException("error!");
    }

    /**
     * 当前方法没有事务，被调用的两个方法有事务，并且事务传播类型是默认（REQUIRED）
     * 被调用的两个方法事务会分别提交
     */
    public void addErrorOutWithoutTransactionInternalWithTransactionDefault() {
        LOGGER.info("addErrorOutWithoutTransactionInternalWithTransactionDefault");
        userBadgeManager.addWithTransactionDefault();
        userBadgeLogManager.addWithTransactionDefault();
        throw new RuntimeException("error!");
    }

    /**
     * 当前方法有事务，并且事务传播类型是默认（REQUIRED），被调用的两个方法有事务，并且事务传播类型是默认（REQUIRED）
     * 发生异常回回滚
     * debug信息：Creating new transaction with name [...]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT，这个是外层的方法的事务
     * debug信息：Rolling back JDBC transaction on Connection [...]
     */
    @Transactional
    public void addErrorOutWithTransactionDefaultInternalWithTransactionDefault() {
        LOGGER.info("addErrorOutWithTransactionDefaultInternalWithTransactionDefault");
        userBadgeManager.addWithTransactionDefault();
        userBadgeLogManager.addWithTransactionDefault();
        throw new RuntimeException("error!");
    }
}
