package br.com.jacto.schedulerservice.exceptions;

import br.com.jacto.schedulerservice.utils.LanguageUtils;

import java.io.Serial;

public class ThirdPartyIntegrationException extends SchedulerException {
    @Serial
    private static final long serialVersionUID = 1570074909437016330L;

    public ThirdPartyIntegrationException() {
        super(LanguageUtils.THIRD_PARTY_INTEGRATION_ERROR);
    }
}
