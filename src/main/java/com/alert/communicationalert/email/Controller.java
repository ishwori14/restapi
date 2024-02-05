package com.alert.communicationalert.email;
import com.alert.communicationalert.email.data.dto.EmailResponse;
import com.alert.communicationalert.email.data.dto.PolicyDetail;
import com.alert.communicationalert.email.data.entity.PolicyEntity;
import com.alert.communicationalert.email.data.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Service
@RequestMapping("/email")
public class Controller {
    @Autowired
    com.alert.communicationalert.email.Service service;
    @GetMapping("/getEmail")
    public ResponseEntity<PolicyDetail> getEmail(@RequestParam String policynumber)
    {
        PolicyDetail policyDetail = new PolicyDetail(); // return to Postman
        PolicyEntity entity = new PolicyEntity();  // get info from the list
        try{
        //call service and get entity
            entity = service.getPolicy(policynumber);
            //set dto from entity
            policyDetail.setEmailAddress(entity.getEmailAddress());
            policyDetail.setFullName(entity.getFullName());
            policyDetail.setPolicyNumber(entity.getPolicyNumber());
            policyDetail.setDate(entity.getPolicyDate());
            policyDetail.setType(entity.getPolicyType());
            Product product = new Product();
            product.setProductCode(entity.getProduct().getProductCode());
            product.setProductName(entity.getProduct().getProductName());
            policyDetail.setProduct(product);
            //return dto;
            return new ResponseEntity<>(policyDetail, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(policyDetail, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getAllEmails")
    public List<PolicyDetail> getAllEmails()
    {
        List<PolicyDetail> policyDetailList = new ArrayList<>(); // return to Postman
        List<PolicyEntity> entityList = new ArrayList<>();  // get info from the list
        try{
            //call service and get entity
            entityList = service.getAllPolicy();
            //set dto from entity
            for (PolicyEntity e: entityList) {
                PolicyDetail policyDetail = new PolicyDetail();
                policyDetail.setEmailAddress(e.getEmailAddress());
                policyDetail.setFullName(e.getFullName());
                policyDetail.setPolicyNumber(e.getPolicyNumber());
                policyDetail.setDate(e.getPolicyDate());
                policyDetail.setType(e.getPolicyType());
                Product product = new Product();
                product.setProductCode(e.getProduct().getProductCode());
                product.setProductName(e.getProduct().getProductName());
                policyDetail.setProduct(product);
                policyDetailList.add(policyDetail);
            }
            //return dto;
            return new ResponseEntity<>(policyDetailList, HttpStatus.OK).getBody();
        }catch (Exception ex){
            return new ResponseEntity<>(policyDetailList, HttpStatus.INTERNAL_SERVER_ERROR).getBody();
        }
    }
    @PostMapping("/postEmail")
    public ResponseEntity<EmailResponse> postEmail(@RequestBody PolicyDetail policy)//get policy detail from 
                                                                                //postman set in entity and save to DB
    {
        EmailResponse response = new EmailResponse();
        try{
            PolicyEntity entity = new PolicyEntity();
            entity.setEmailAddress(policy.getEmailAddress());
            entity.setFullName(policy.getFullName());
            entity.setPolicyNumber(policy.getPolicyNumber());
            entity.setPolicyDate(policy.getDate());
            entity.setPolicyType(policy.getType());
            Product product =new Product();
            product.setProductCode(policy.getProduct().getProductCode());
            product.setProductName(policy.getProduct().getProductName());
            entity.setProduct(product);
            service.addPolicy(entity);
            response.setStatusCode("201");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception ex){
            response.setStatusCode("500");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
