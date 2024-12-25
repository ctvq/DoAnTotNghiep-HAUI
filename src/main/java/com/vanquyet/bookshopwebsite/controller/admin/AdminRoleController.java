package com.vanquyet.bookshopwebsite.controller.admin;

import com.vanquyet.bookshopwebsite.controller.common.BaseController;
import com.vanquyet.bookshopwebsite.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/admin/roles_management")
public class AdminRoleController extends BaseController {
    private final RoleService roleService;
}

