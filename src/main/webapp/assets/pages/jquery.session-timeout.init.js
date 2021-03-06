/**
 * Theme: Amezia - Responsive Bootstrap 4 Admin Dashboard
 * Author: Themesbrand
 * Session Timeout
 */
$.sessionTimeout({
    keepAliveUrl: 'page-starter.html',
    logoutButton:'Logout',
    logoutUrl: 'login.jsp',
    redirUrl: 'auth-lock-screen.html',
    warnAfter: 3000,
    redirAfter: 30000,
    countdownBar: true
});
