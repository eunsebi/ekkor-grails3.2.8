import xyz.ekkor.CustomUserDetailService
import xyz.ekkor.OldPasswordEncoder
import xyz.ekkor.listeners.CustomSecurityEventListener

// Place your Spring DSL code here
beans = {
    userDetailsService(CustomUserDetailService)
    //securityEventListener(CustomSecurityEventListener)
    //passwordEncoder(OldPasswordEncoder)
}
