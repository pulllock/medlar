package me.cxis.spring.transaction.controller;

import jakarta.annotation.Resource;
import me.cxis.spring.transaction.service.UserBadgeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring/transaction")
public class UserBadgeController {

    @Resource
    private UserBadgeService userBadgeService;

    @GetMapping("/addSuccessWithoutTransaction")
    public void addSuccessWithoutTransaction() {
        userBadgeService.addSuccessWithoutTransaction();
    }

    @GetMapping("/addErrorWithoutTransaction")
    public void addErrorWithoutTransaction() {
        userBadgeService.addErrorWithoutTransaction();
    }

    @GetMapping("/addErrorWithTransactionDefault")
    public void addErrorWithTransactionDefault() {
        userBadgeService.addErrorWithTransactionDefault();
    }

    @GetMapping("/addErrorWithTransactionPropagationSupports")
    public void addErrorWithTransactionPropagationSupports() {
        userBadgeService.addErrorWithTransactionPropagationSupports();
    }

    @GetMapping("/addErrorWithTransactionPropagationMandatory")
    public void addErrorWithTransactionPropagationMandatory() {
        userBadgeService.addErrorWithTransactionPropagationMandatory();
    }

    @GetMapping("/addErrorWithTransactionPropagationRequiresNew")
    public void addErrorWithTransactionPropagationRequiresNew() {
        userBadgeService.addErrorWithTransactionPropagationRequiresNew();
    }

    @GetMapping("/addErrorWithTransactionPropagationNotSupported")
    public void addErrorWithTransactionPropagationNotSupported() {
        userBadgeService.addErrorWithTransactionPropagationNotSupported();
    }

    @GetMapping("/addErrorWithTransactionPropagationNever")
    public void addErrorWithTransactionPropagationNever() {
        userBadgeService.addErrorWithTransactionPropagationNever();
    }

    @GetMapping("/addErrorWithTransactionPropagationNested")
    public void addErrorWithTransactionPropagationNested() {
        userBadgeService.addErrorWithTransactionPropagationNested();
    }

    @GetMapping("/addErrorOutWithoutTransactionInternalWithTransactionDefault")
    public void addErrorOutWithoutTransactionInternalWithTransactionDefault() {
        userBadgeService.addErrorOutWithoutTransactionInternalWithTransactionDefault();
    }

    @GetMapping("/addErrorOutWithTransactionDefaultInternalWithTransactionDefault")
    public void addErrorOutWithTransactionDefaultInternalWithTransactionDefault() {
        userBadgeService.addErrorOutWithTransactionDefaultInternalWithTransactionDefault();
    }
}
