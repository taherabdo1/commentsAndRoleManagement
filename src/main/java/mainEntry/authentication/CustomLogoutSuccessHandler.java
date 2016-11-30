package mainEntry.authentication;

public class CustomLogoutSuccessHandler extends
SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

  @Autowired
  private AuditService auditService; 

  @Override
  public void onLogoutSuccess
    (HttpServletRequest request, HttpServletResponse response, Authentication authentication) 
    throws IOException, ServletException {
      String refererUrl = request.getHeader("Referer");
      auditService.track("Logout from: " + refererUrl);

      super.onLogoutSuccess(request, response, authentication);
  }
}