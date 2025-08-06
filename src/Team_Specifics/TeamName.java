package Team_Specifics;

public enum TeamName {
    MANCHESTER_UNITED(Leagues.EPL),
    LIVERPOOL(Leagues.EPL),
    ARSENAL(Leagues.EPL),
    CHELSEA(Leagues.EPL),
    MANCHESTER_CITY(Leagues.EPL),

    BARCELONA(Leagues.LA_LIGA),
    REAL_MADRID(Leagues.LA_LIGA),
    ATLETICO_MADRID(Leagues.LA_LIGA),
    ATHLETIC_BILBAO(Leagues.LA_LIGA),
    SEVILLA(Leagues.LA_LIGA),

    BAYERN_MUNCHEN(Leagues.BUNDESLIGA),
    BORRUSIA_DORTMUND(Leagues.BUNDESLIGA),
    BAYER_LEVERKUSEN(Leagues.BUNDESLIGA),
    EINTRACHT_FRANKFURT(Leagues.BUNDESLIGA),
    RB_LEIPZIG(Leagues.BUNDESLIGA),

    INTER_MILAN(Leagues.SERIE_A),
    AC_MILAN(Leagues.SERIE_A),
    NAPOLI(Leagues.SERIE_A),
    JUVENTUS(Leagues.SERIE_A),
    AS_ROMA(Leagues.SERIE_A),

    PSG(Leagues.LIGUE_1),
    OLYMPIQUE_LYONNAIS(Leagues.LIGUE_1),
    OLYMPIQUE_DE_MARSAILLE(Leagues.LIGUE_1),
    LOSC_LILLE(Leagues.LIGUE_1),
    AC_MONACO(Leagues.LIGUE_1);

    private final Leagues league;

    TeamName(Leagues league) {
        this.league = league;
    }

    public Leagues getLeague() {
        return this.league;
    }
}
