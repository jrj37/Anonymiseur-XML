package Start;

import Graphics.*;
import java.io.IOException;

public class RunAlgo {
    public RunAlgo() throws IOException {
    Initialisation initialisation = new Initialisation();
    ENDRGB affichage=new ENDRGB();

        initialisation.Start();
        affichage.affichage();
    }
}
