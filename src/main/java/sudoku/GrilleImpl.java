package sudoku;

/**
 * . classe GrilleImp
 */
public class GrilleImpl implements Grille {
    /**
     * .
     *
     * @param tail
     */
    static final int TAIL_GRILLE = 9;
    /**
     * .
     *
     * @param grille
     */
    private static char[][] grilleSu = new char[TAIL_GRILLE][TAIL_GRILLE];
    /**
     * .
     *
     * @param taille du tableau
     */
    private int t;

    /**
     * .
     *
     * @return char
     */
    public final char[][] getGrille() {
        return grilleSu;
    }

    /**
     *
     * @param grille char[][]
     */
    public final void setGrille(final char[][] grille) {
        this.grilleSu = grille;
    }

    /**
     *
     * @return taille
     */
    public final int getTaille() {
        return t;
    }

    /**
     *
     * @param taille int
     */
    public final void setTaille(final int taille) {
        this.t = taille;
    }

    /**
     * . constructeur
     *
     * @param taille int
     */
    public GrilleImpl(final int taille) {
        this.t = taille;
        // grille = new char[taille][taille];
    }

    /**
     * . retourne la taille de la grille
     *
     * @return taille
     */
    public final int getDimension() {
        // TODO Auto-generated method stub
        return grilleSu.length;
    }

    /**
     * set value.
     *
     * @param x     int
     * @param y     int
     * @param value char
     * @throws IllegalArgumentException Exception
     */
    public final void setValue(final int x, final int y,
            final char value) throws IllegalArgumentException {
        boolean fOk = false;
        // TODO Auto-generated method stub
        // si le nombre de ligne dépasse la taille de la grille
        if (x > this.t) {
            throw new IllegalArgumentException();
        }
        // si le nombre de colonne dépasse la taille de la grille
        if (y > this.t) {
            throw new IllegalArgumentException();
        }
        // on cherche dans le tableau est ce que la valeur est possible selon
        // la taille du grille
        for (int i = 0; i < this.t; i++) {
            if (value == POSSIBLE[i]) {
                fOk = true;
            }
        }
        if ((!fOk) || !possible(x, y, value)) {
            throw new IllegalArgumentException();
        }
        // affectation de la valeur
        grilleSu[x][y] = value;
    }

    /**
     *
     * @param x position x dans la grille
     * @param y position y dans la grille
     * @return valeur dans la case x,y
     * @throws IllegalArgumentException si x ou y sont hors bornes
     */
    public final char getValue(final int x,
            final int y) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        // si le nombre de ligne dépasse la taille de la grille
        if (x > this.t - 1) {
            throw new IllegalArgumentException("taille depassee");
        }
        // si le nombre de colonne dépasse la taille de la grille
        if (y > this.t - 1) {
            throw new IllegalArgumentException("taille depassee");
        }
        return grilleSu[x][y];
    }
    /**
     * . Test si une grille est terminee
     *
     * @return true si la grille est complete
     */
    public final boolean complete() {
        // TODO Auto-generated method stub
        for (int i = 0; i < grilleSu.length; i++) {
            // si on a encore une case qui egale au caractre vide
            // donc la grille n'est pas complete
            for (int j = 0; j < grilleSu[i].length; j++) {
                if (grilleSu[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     *
     * @param x     position x dans la grille
     * @param y     position y dans la grille
     * @param value valeur a mettre dans la case
     * @return true si on peut mettre la valeur
     * @throws IllegalArgumentException si x ou y sont hors bornes
     * @throws IllegalArgumentException si
     * la value n'est pas un caractère autorisé
     * ('1',....,'9')
     */
    public final boolean possible(final int x, final int y,
            final char value) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        boolean existeColonne = false;
        boolean existeLigne = false;
        boolean existeCarre = false;
        boolean fOk = false;
        final int v9 = 9;
        final int v3 = 3;
        final int v4 = 4;
        final int v16 = 16;
        // TODO Auto-generated method stub
        // si le nombre de ligne dépasse la taille de la grille
        if (x > this.t - 1) {
            throw new IllegalArgumentException("taille depassee");
        }
        // si le nombre de colonne dépasse la taille de la grille
        if (y > this.t - 1) {
            throw new IllegalArgumentException("taille depassee");
        }
        // on cherche dans le tableau est ce que la valeur est possible
        // selon la taille de la grille
        for (int i = 0; i < this.t; i++) {
            if (value == POSSIBLE[i]) {
                fOk = true;
            }
        }
        if (!fOk) {
            throw new IllegalArgumentException("valeur non autorise");
        }
        int r;
        int c;
        int n = 0;
        for (int i = 0; i < t; i++) {
            if (grilleSu[x][i] == value) {
                existeLigne = true;
            }
        }
        for (int i = 0; i < t; i++) {
            if (grilleSu[i][y] == value) {
                existeColonne = true;
            }
        }
        if (t == v9) {
            n = v3;
        }
        if (t == v16) {
            n = v4;
        }
        r = x - x % n;
        c = y - y % n;
        for (int i = r; i < r + n; i++) {
            for (int j = c; j < c + n; j++) {
                if (grilleSu[i][j] == value) {
                    existeCarre = true;
                }
            }
        }
        if ((!existeLigne) && (!existeColonne)
                && (!existeCarre)
                && this.grilleSu[x][y] == EMPTY) {
            return true;
        }
        return false;
    }
}
