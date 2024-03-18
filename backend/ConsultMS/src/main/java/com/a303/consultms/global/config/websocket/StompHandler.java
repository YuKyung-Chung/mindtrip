//package com.a303.consultms.global.config.websocket;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class StompHandler {
//    @Override
//    public Message<?> preSend(Message<?> message, MessageChannel channel) {
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//
//        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
//            Map<String, Object> payload = validateAccessToken(
//                Objects.requireNonNull(accessor.getFirstNativeHeader("Authorization")));
//
//            System.out.println(payload);
//            String memberId = (String) payload.get("memberId");
//
//            UserDetails userDetails = apiUserDetailsService.loadUserByUsername(memberId);
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                userDetails, null, userDetails.getAuthorities()
//            );
//
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        }
//        return message;
//    }
//
//    private Map<String, Object> validateAccessToken(String headStr) {
//        if (Objects.isNull(headStr)) {
//            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.UNACCEPT);
//        }
//        String tokenType = headStr.substring(0, 6);     // BEARER
//        String tokenStr = headStr.substring(7);
//
//        if (!tokenType.equalsIgnoreCase("BEARER")) {
//            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.BADTYPE);
//        }
//
//        try {
//            return jwtUtil.validateToken(tokenStr);
//        } catch (MalformedJwtException malformedJwtException) {
//            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.MALFORM);
//        } catch (SignatureException signatureException) {
//            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.BADSIGN);
//        } catch (ExpiredJwtException expiredJwtException) {
//            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.EXPIRED);
//        }
//    }
//}
