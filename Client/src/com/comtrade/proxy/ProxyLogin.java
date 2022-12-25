package com.comtrade.proxy;

import com.comtrade.domain.User;
import com.comtrade.view.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

public class ProxyLogin implements Proxy {

    @Override
    public void login(User user) throws IOException, InvocationTargetException, InterruptedException, ClassNotFoundException {
        Set<String> setRoleName = user.getSetRoleUser().stream().map(rola -> rola.getRole_name()).collect(Collectors.toSet());

        if (setRoleName.contains("Собственник")) {
            OwnerForm ownerForm = new OwnerForm(user);
            ownerForm.setVisible(true);

        } else if (setRoleName.contains("Официант") ) {
            WaiterForm waiterEntryForm = new WaiterForm(user);
            waiterEntryForm.setVisible(true);
        }
    }
}