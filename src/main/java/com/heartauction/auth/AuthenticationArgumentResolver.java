package com.heartauction.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AuthenticationArgumentResolver implements HandlerMethodArgumentResolver,
        org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Login.class) && parameter.getParameterType().equals(LoginMember.class);
    }

    @Override
    public LoginMember resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        return (LoginMember) request.getAttribute(LoginMember.key);
    }

    @Override
    public LoginMember resolveArgument(MethodParameter parameter, Message<?> message) throws Exception {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);
        return (LoginMember) headerAccessor.getSessionAttributes().get(LoginMember.key);
    }
}
