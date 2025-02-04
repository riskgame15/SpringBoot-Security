package com.springbootsecurity.service.role;


import com.springbootsecurity.model.AppRole;
import com.springbootsecurity.service.GeneralService;

public interface IAppRoleService extends GeneralService<AppRole> {
    AppRole findByName(String name);
}
