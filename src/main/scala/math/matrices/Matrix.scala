package math.matrices

/**
	* Matrix as a array container for Type T
	*/
trait Matrix[T] extends Serializable{
	
	/**
	* Number of Rows
	*/
	val rowNum:Int
	
/**
	* Number of Columns
	*/
	val colNum:Int
/**
	* Array containing Type T
	*/
	val array:Array[T]
	
	val rows:Array[Vector[T]] = {for{row <- 0 until rowNum} yield array.slice(row * colNum, (row + 1) * colNum).toVector}.toArray
	
	val cols:Array[Vector[T]] = {for{col <- 0 until colNum} yield rows.flatMap(ls => ls.slice(col, col + 1)).toVector}.toArray
	
/**
	* method apply to whole array
	*/
	def map[B](f:T=>B):Iterable[B] =array.map(f)
	
/**
	*  Unit method apply to whole array
	*/
	def foreach(f:T=>Unit):Unit = array.foreach(f)
	
/**
	* Extract data from array[i][j]
	* @param i index of row
	* @param j index of column
	*/
	def apply(i:Int,j:Int):T = array(index(i,j))
/**
	* Update data to array[i][j]
	* @param i index of row
	* @param j index of column
	* @param value value to update
	*/
	def update(i:Int,j:Int,value:T):Unit = array(index(i,j)) = value
	
	def index(i:Int,j:Int):Int = i * rowNum + j

	
/**
	* Matrix scala operation
	*/
	def +(that:T):Matrix[T]
	def -(that:T):Matrix[T]
	def *(that:T):Matrix[T]
	def /(that:T):Matrix[T]
	
/**
	* Helper Method for caculation that implicitly provided by Class[T]
	*/
	def plus:(T,T)=>T
	def minus:(T,T)=>T
	def times:(T,T)=>T
	def divide:(T,T)=>T
	

}


