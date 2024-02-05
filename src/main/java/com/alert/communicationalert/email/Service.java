package com.alert.communicationalert.email;

import com.alert.communicationalert.email.data.entity.CustomerEmail;
import com.alert.communicationalert.email.data.entity.PolicyEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class Service {
    List<PolicyEntity> entityList = new ArrayList<>();
    public void addPolicy(PolicyEntity entity){
        entityList.add(entity);
    }
    public PolicyEntity getPolicy(String policyNumber){
       return entityList.stream()
               .filter(m -> m.getPolicyNumber().equals(policyNumber)).collect(Collectors.toList()).stream().findFirst().orElse(null);
    }

    public List<PolicyEntity> getAllPolicy() {
        return entityList;
    }
}
