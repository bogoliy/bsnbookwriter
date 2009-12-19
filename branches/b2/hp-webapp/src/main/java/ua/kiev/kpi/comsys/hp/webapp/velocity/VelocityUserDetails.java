package ua.kiev.kpi.comsys.hp.webapp.velocity;



import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

public class VelocityUserDetails {

    /**
     * Get the username of the user
     *
     * @return the username of the user
     */
    public static String getPrincipal() {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (obj instanceof UserDetails) {
            return ((UserDetails) obj).getUsername();
        } else {
            return "guest";
        }
    }

    /**
     * Is the user granted all of the supplied roles
     *
     * @param roles a string array of roles
     * @return true if user has all of the listed roles, otherwise false
     */
    public static boolean allGranted(String[] roles) {
        Set<String> userRoles = getUserRoles();
        for (String role : roles) {
            if (userRoles.contains(role))
                continue;
            return false;
        }
        return true;
    }

    /**
     * Is the user granted any of the supplied roles
     *
     * @param roles a string array of roles
     * @return true if user has any of the listed roles, otherwise false
     */
    public static boolean anyGranted(String[] roles) {
        Set<String> userRoles = getUserRoles();
        for (String role : roles) {
            if (userRoles.contains(role))
                return true;
        }
        return false;
    }

    /**
     * is the user granted none of the supplied roles
     *
     * @param roles a string array of roles
     * @return true only if none of listed roles are granted
     */
    public static boolean noneGranted(String[] roles) {
        Set<String> userRoles = getUserRoles();
        for (String role : roles) {
            if (userRoles.contains(role))
                return false;
        }
        return true;
    }

    private static Set<String> getUserRoles() {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<String> roles = new HashSet<String>();
        if (obj instanceof UserDetails) {
            GrantedAuthority[] gas = ((UserDetails) obj).getAuthorities();
            for (GrantedAuthority ga : gas) {
                roles.add(ga.getAuthority());
            }
        }
        return roles;
    }
}