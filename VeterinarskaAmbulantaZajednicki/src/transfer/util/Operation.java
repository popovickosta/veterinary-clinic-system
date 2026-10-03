package transfer.util;

import java.io.Serializable;

/**
 *
 * @author Kosta
 */
public enum Operation implements Serializable {
    LOGIN,
    LOGOUT,
    GET_ALL_MESTO,
    GET_ALL_USLUGA,
    ADD_USLUGA,
    SEARCH_USLUGA,
    UPDATE_USLUGA,
    DELETE_USLUGA,
    GET_ALL_STRUCNA_SPREMA,
    ADD_STRUCNA_SPREMA,
    GET_ALL_VLASNIK,
    ADD_VLASNIK,
    SEARCH_VLASNIK,
    UPDATE_VLASNIK,
    DELETE_VLASNIK,
    GET_ALL_ZAPOSLENI,
    GET_ALL_RACUN,
    ADD_RACUN,
    SEARCH_RACUN,
    UPDATE_RACUN
}
