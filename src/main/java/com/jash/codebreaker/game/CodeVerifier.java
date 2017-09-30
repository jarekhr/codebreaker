package com.jash.codebreaker.game;

import com.jahs.codebreaker.model.Code;
import com.jahs.codebreaker.model.Response;

public interface CodeVerifier {

    /**
     * Verifies a guess against a secret.
     *
     * @param secret
     * @param guess
     * @return
     */
    Response verifyCode(Code secret, Code guess);

}
