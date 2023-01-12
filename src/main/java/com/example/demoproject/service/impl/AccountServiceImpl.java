package com.example.demoproject.service.impl;

import com.example.demoproject.dao.AccountDAO;
import com.example.demoproject.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDAO accountDAO;
    private BigDecimal maxValue;
    private BigDecimal startValue = new BigDecimal(120);
    private BigDecimal currentValue;
    private Integer percetnsForIncrees = 10;

    @Autowired
    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    //Раз в 30 секунд BALANCE каждого клиента увеличиваются на 10% но не более 207% от начального депозита.
    //Например:
    //Было: 100, стало: 110.
    //Было: 110, стало:121.
    //…
    //Окончательное значение: 194.87

    // здесь по хорошему нужно использовать нужно спользовать Redis для хранения начального, максимально, и текущего значения
    // или бд
    @Scheduled(fixedDelay = 30_00)
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void increasingBalanceByTimer() {
        //first value  = 100
        // maxval = (value / 100 * 207)
        //res = firstvalue / 10
        // firstvalue + res

        maxValue = startValue.divide(new BigDecimal(100)).multiply(new BigDecimal(207));
        System.out.println(maxValue + " MAX VALUE");


        if(currentValue == null) {
            currentValue = startValue;
        }

        BigDecimal increaser =  currentValue.divide(new BigDecimal(10));

        BigDecimal rr = currentValue.add(increaser);

        if(rr.compareTo(maxValue) > 0) {
            //finish
        }

    }
}
