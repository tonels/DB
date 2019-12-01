package com.dbDemo2.controller;

import com.dbDemo2.model.db1.Office;
import com.dbDemo2.model.db1.CustomersEntity;
import com.dbDemo2.model.db2.HdMem;
import com.dbDemo2.model.db3.Member;
import com.dbDemo2.repository.db1.CardRepository;
import com.dbDemo2.repository.db1.CustomerRepo;
import com.dbDemo2.repository.db2.CardHolderRepository;
import com.dbDemo2.repository.db3.MemberRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping
public class TeController {

    @Resource
    private CardRepository cardRepository;
    @Resource
    private CardHolderRepository cardHolderRepository;
    @Resource
    private MemberRepository memberRepository;
    @Resource
    private CustomerRepo customerRepo;

    @GetMapping("/card")
    public String o1(){
        List<Office> all = cardRepository.findAll();
        return all.toString().toUpperCase();
    }

    @GetMapping("/customer")
    public String c1(){
        List<CustomersEntity> all1 = customerRepo.findAll();
        return all1.toString().toUpperCase();
    }

    @GetMapping("/holder")
    public String h1(){
        List<HdMem> all1 = cardHolderRepository.findAll();
        return all1.toString().toUpperCase();
    }

    @GetMapping("/member")
    public String m1(){
        List<Member> list = memberRepository.findAll();
        return list.toString().toUpperCase();
    }

}
