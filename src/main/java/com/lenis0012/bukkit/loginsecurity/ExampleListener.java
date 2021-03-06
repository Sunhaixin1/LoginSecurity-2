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

package com.lenis0012.bukkit.loginsecurity;

import com.lenis0012.bukkit.loginsecurity.events.AuthActionEvent;
import com.lenis0012.bukkit.loginsecurity.session.AuthAction;
import com.lenis0012.bukkit.loginsecurity.session.AuthActionType;
import com.lenis0012.bukkit.loginsecurity.session.AuthService;
import com.lenis0012.bukkit.loginsecurity.session.PlayerSession;
import com.lenis0012.bukkit.loginsecurity.session.action.RemovePassAction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * An example listener using the LoginSecurity 2.1 API.
 *
 * This listeners makes it so whenever a player logs out using /logout.
 * It doesn't log out but instead deletes their password.
 *
 * Pretty useless, but it's just an example.
 */
public class ExampleListener implements Listener {

    @EventHandler
    public void onAuthAction(AuthActionEvent event) {
        // Check if type is logout
        if(event.getType() == AuthActionType.LOGOUT) {
            // Check if ran by player
            if(event.getAction().getService() != AuthService.PLAYER) {
                return;
            }

            // Prevent player from logging out
            event.setCancelled(true);

            // Get player's session
            PlayerSession session = event.getSession();

            // Create action to remove password authentication by your plugin
            AuthAction deletePassword = new RemovePassAction(AuthService.PLUGIN, LoginSecurity.getInstance()); // Use your plugin instead

            // Run the action
            session.performAction(deletePassword);
        }
    }
}
