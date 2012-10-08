(ns cafe5.ui.cli.logo)
(use 'colorize.core
     '[cafe5.protocols.feedback])

(def the-logo
  (blue
    "\n"
    "\n"
    "          ..,,'.               .,'. .','                       .,;,.                                            \n"
    "       cON0l'cKd            lXNXk0OXNKkK,                  .kNO;';00.                    o00c              ..'\n"
    "     .ONNx   :NO.          lWWX. dWWO. '.                 .0WNc  .00.                    o0O:             lNWO.\n"
    "    .0WWk    ..   ,lddo'  .KWW0loXNNkl, .cdoo:   .codoc    lNNNx'..    .:odol. 'ooc'co: ,oo:  ,KXXoldo;  cKWNk; \n"
    "    dWWX'       :KN0''0N; cNWX' lNWO. .xNXc.dN;.kNNo.cN;    'ONNNO;  .oNNk.cNl.0WNkcxN0'OWNl  dWW0;'kNWo lNW0. \n"
    "   .KNWk       lNNX. ,XNxlKNNd .0WNc .0WN:.lKo.OWNl.:Ko  'cl:.;KNNN: kWWk  .. :NWK.  . ;NWK. .KWN;  lNWd.0WWo \n"
    "   .XNWx      .XWWo  'X0.'NNX' cNN0. dNWXol;. cNWXdl:.  xNNd,. ;NNWd;NNN,    .OWWd     OWNl  lWNO  .OWN;:NWX. \n"
    "   .0WWX:.  .;kNNWo.;KK, xNWx .0WNc  xNW0.  .cONWK,..:..KWK.   oNWX,lNNN:  .:ONNN,    ,XNN;.lXNNc 'kNNo OWWO.\n"
    "    .dKXNXKOx:.:kKKKkc..;XWNo,dNWK.  .d0XKOxl..oKXX0x,  'xKXOOKXOl. .o0XX0kl,dKKx      oKX0dONWKOKXKx,  :0XKc \n"
    "      ...          .lo:xNWk .OWNd                         ....                            .0WN:              \n"
    "                    lXolXWNx;xNW0.                      "  (white "        'lll.    ;d'   ")  (blue "           cNWK. \n")
    (blue "                     ;odo:.cddo;                             ")  (white "  'Wl      .:  ")  (blue "              cl:. \n")
    (white "                                           .lxllll.  ;ccllxl  ;xMkl; .odlclxo.    \n"
    "                                          .KK.       .:lookM;  ,M:  .KXc:::oWk    \n"
    "                                          .X0       ;Nc...:M:  ,M:  .N0,'''''.    \n"
    "                                           .kOc;;l. 'Xx;;lOM:  ,M:   ,0kc;;:o'    \n"
    "                                             .','.    ','. '    '      .','..     \n"
    "\n"))
  )


(defn show-logo [fb] (murmur fb the-logo))