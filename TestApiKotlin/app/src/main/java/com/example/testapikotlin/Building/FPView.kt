import android.content.Context
import android.widget.Toast
import java.security.InvalidParameterException

/** FP View : First person view
 * This is the main mechanical class for the personnalized indoor street view of Buildings.
 * it has a fully fonctionnal hovering system over an array that simulate the map
 * And images are generated using this displayID function that will retrieve the name of the image in drawable-xxxhdpi location
 *
 * Important to know :  -every image must be correctly named
 *                      -The array is composed of 0 where there is no destination where we are facing, or any number for each position that we face
 *
 * @param direction is a chat to set the starting direction : 'N' north, 'E' east, 'W' west, 'S' South
 * (corresponds to second row (column) of the map table)
 * @param position is the starting position (corresponds to first row of map table)
 * @param where to setup the building we are visiting
 * @param context, the context of the activity (must have)
 *
 * @author Mainly Adam.
 */

class FPView(private var direction: Char, private var position: Int, val where: String, private val context: Context) {

    lateinit private var theMap: Array<IntArray>

    /**
     * Initialisation of the map depends on the constructor of VisitBuilding.
     * Map is hard-coded directly here as we didn' find any interest of doing additionnal classes for that.
     */
    init {
        when (where) {
            "belval_university" -> theMap =  arrayOf(intArrayOf(0, 0, 0, 0), intArrayOf(2, 0, 0, 0), intArrayOf(0, 3, 6, 1), intArrayOf(4, 2, 0, 0), intArrayOf(0, 0, 5, 3), intArrayOf(0, 4, 0, 4),
                    intArrayOf(0, 7, 0 ,2), intArrayOf(12, 0, 0, 8), intArrayOf(7, 0, 9, 0), intArrayOf(10, 8, 0, 0), intArrayOf(11, 0, 13, 9),
                    intArrayOf(0, 0, 12, 10),intArrayOf(0, 0, 11, 7), intArrayOf(17, 0, 10, 14), intArrayOf(13, 0, 15, 0), intArrayOf(16, 14, 0, 0),
                    intArrayOf(0, 17, 0, 15), intArrayOf(0, 0, 16, 13))
            "belval_basicfit" -> theMap =  arrayOf(intArrayOf(0, 0, 0, 0))
            "belval_learning_center" -> theMap =  arrayOf(intArrayOf(0, 0, 0, 0), intArrayOf(2, 0, 0, 0), intArrayOf(3, 0, 0, 0), intArrayOf(4, 0, 0, 2), intArrayOf(5, 0, 6, 3), intArrayOf(0, 4, 0, 0),
                intArrayOf(0, 7, 10 ,4), intArrayOf(0, 8, 6, 0), intArrayOf(7, 9, 7, 0), intArrayOf(0, 0, 10, 0), intArrayOf(0, 11, 0, 6),
                intArrayOf(13, 14, 12, 10),intArrayOf(0, 11, 0, 0), intArrayOf(0, 0, 0, 11), intArrayOf(0, 15, 11, 0), intArrayOf(14, 0, 14, 0))
        }
    }

    /**Function that return a integer : ID of the drawable of a certain name
     * This function assembles every parameters, combine them to find the appropriate image. As follows :
     * "{where} p{position}d{direction as integer}"
     *
     * example : belval_plaza_p1d2
     */
    fun displayID(): Int {
        val uri = "${where}_p${position}d${charToInt()}" // where $ (without the extension) is the file
        return context.getResources().getIdentifier(uri, "drawable", context.getPackageName())
    }

    fun checkDestination(): Boolean {
        return (theMap[position][charToInt()-1]) > 0
    }
    /**
     * Function that swaps the direction to the right
     */
    fun turnRight(): Unit {
        do {
            when (direction) {
                'N' -> direction = 'E'
                'E' -> direction = 'S'
                'W' -> direction = 'N'
                'S' -> direction = 'W'
                else -> throw InvalidParameterException("illegal char spotted")
            }
        } while (displayID() == 0)

    }

    /**
     * Function that swaps the direction to the left
     */
    fun turnLeft(): Unit {
        do {
            when (direction) {
                'N' -> direction = 'W'
                'E' -> direction = 'N'
                'W' -> direction = 'S'
                'S' -> direction = 'E'
                else -> throw InvalidParameterException("illegal char spotted")
            }
        } while (displayID() == 0)
    }

    /**
     * Function that return an int for the direction :
     * 'N' = 1, 'E' = 2, 'W' = 3, 'S' = 4
     */

    fun charToInt(): Int = when (direction) {
        'N' -> 1
        'E' -> 2
        'W' -> 3
        'S' -> 4
        else -> throw InvalidParameterException("illegal char spotted")
    }

    /**
     * Function that swaps the postion using theMap table pointer
     * If theMap points to 0, the position doesn't exist and a toast shows up.
     */

    fun goForward() {
        if ((theMap[position][charToInt()-1]) > 0) {
            position = theMap[position][charToInt()-1]
            if (displayID() == 0) direction = 'E'
            if (displayID() == 0) direction = 'W'
            if (displayID() == 0) direction = 'S'
        } else Toast.makeText(context.applicationContext, "Cannot go there !", Toast.LENGTH_LONG).show()
    }

}