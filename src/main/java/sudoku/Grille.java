package sudoku;

/**.
 * Interface d'une grille sudoku .
 */
public interface Grille {
/**.
*Caractere de case vide
*/
char EMPTY = '@';
/**caractere possible a mettre dans la grille.
*pour une grille 9x9 : 1..9
*pour une grille 16x16 : 0..9-a..f
*/
char[] POSSIBLE = new char[] {'1', '2', '3', '4', '5', '6', '7',
'8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f'};
/**
 *
 * @return largeur/hauteur de la grille
 */
int getDimension();
/**
 *
 * @param x position x dans la grille
 * @param y position y dans la grille
 * @param value valeur a mettre dans la case
 *
 * @throws IllegalArgumentException si x ou y sont hors bornes
 * @throws IllegalArgumentException si la valeur est interdite aux vus des
 * autres valeurs de la grille
 * @throws IllegalArgumentException si la value n'est pas un caractère autorisé
 * ('1',....,'9')
 */
void setValue(int x, int y, char value) throws
 IllegalArgumentException;
/**
 *
 * @param x position x dans la grille
 * @param y position y dans la grille
 * @return valeur dans la case x,y
 * @throws IllegalArgumentException si x ou y sont hors bornes
 */
char getValue(int x, int y) throws IllegalArgumentException;
/**.
 * Test si une grille est terminee
 * @return true si la grille est complete
 */
boolean complete();
/**
 *
 * @param x position x dans la grille
 * @param y position y dans la grille
 * @param value valeur a mettre dans la case
 * @return true si on peut mettre la valeur
 * @throws IllegalArgumentException si x ou y sont hors bornes
 * @throws IllegalArgumentException si la value n'est pas un caractère autorisé
 * ('1',....,'9')
 */
boolean possible(int x, int y, char value) throws
 IllegalArgumentException;
}
