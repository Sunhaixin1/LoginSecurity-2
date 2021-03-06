/*
 * This file is a part of LoginSecurity.
 *
 * Copyright (c) 2017 Lennart ten Wolde
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lenis0012.bukkit.loginsecurity.session.action;

import com.lenis0012.bukkit.loginsecurity.LoginSecurity;
import com.lenis0012.bukkit.loginsecurity.session.*;
import com.lenis0012.bukkit.loginsecurity.storage.PlayerProfile;

public class RemovePassAction extends AuthAction {
    public <T> RemovePassAction(AuthService<T> service, T serviceProvider) {
        super(AuthActionType.REMOVE_PASSWORD, service, serviceProvider);
    }

    @Override
    public AuthMode run(PlayerSession session, ActionResponse response) {
        if(!session.isRegistered()) {
            throw new IllegalStateException("User is not registered!");
        }
//        LoginSecurity.getInstance().getDatabase().delete(session.getProfile());
        LoginSecurity.dao().getProfileDao().deleteProfile(session.getProfile());
        session.resetProfile();
        return LoginSecurity.getConfiguration().isPasswordRequired() ? AuthMode.UNREGISTERED : AuthMode.AUTHENTICATED;
    }
}
