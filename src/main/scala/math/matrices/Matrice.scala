package math.matrices

abstract class Matrice[T](val rowNum:Int, val colNum:Int, val array:Array[T]) extends Matrix[T] {
	
	import scala.reflect.ClassTag
	
	require(array.length == rowNum * colNum, "The numbers given doesn't match the " +
		s"size of the matrix! size of numbers: ${array.length}, number of rows * number of columns: ${rowNum * colNum}")
	
	def equalSize(that: Matrice[T]): Boolean = this.colNum == that.colNum && this.rowNum == that.rowNum
	/**
		* Check Both Matrix able to do multiplication
		*/
	def operable(that: Matrice[T]): Boolean = this.colNum == that.rowNum
	
	def constructWith(iter:Iterable[T])(implicit rowNum:Int = this.rowNum,colNum:Int = this.colNum,ctag:ClassTag[T]):Matrice[T]
	
	/**
		* Helper method for multiplication
		* for rows and cols apply functions then concate them
		*/
	def interact(that: Matrice[T], f: (T, T) => T, concate: (T, T) => T)(implicit zero: T): Iterable[T] =
		if (operable(that))
			this.rows
				.flatMap(row =>
					that.cols
						.map(column =>
							{row zip column}
								.foldLeft(zero)({ case (sum, (x, y)) => concate(sum, f(x, y)) })))
		else
			throw new NoSuchMethodException(s"The Number of Columns of This Matrix" +
				s" (${this.colNum}) are not equal to Number of Rows of That Matrix (${that.rowNum})")
	
	/**
		* Helper method for Matrix to Matrix Operation
		* apply functionto both array
		*/
	def coOps(that: Matrice[T], f: (T, T) => T): Iterable[T] =
		if (equalSize(that))
			{this.array zip that.array}
				.map { case (l, r) => f(l, r) }
		else
			throw new NoSuchMethodException(s"The Size of two matrix are not equal " +
				s"(${this.array.length} != ${that.array.length})")
	
	def scalaOps(that: T, f: (T, T) => T): Iterable[T] =
		array.map(value => f(value, that))
	
	/**
		* Extract data from array[i][j]
		* @param i index of row
		* @param j index of column
		*/
	override def apply(i:Int,j:Int):T = array(index(i,j))
	/**
		* Update data to array[i][j]
		* @param i index of row
		* @param j index of column
		* @param value value to update
		*/
	override def update(i:Int,j:Int,value:T):Unit = array(index(i,j)) = value
	
	override def index(i:Int,j:Int):Int = i * rowNum + j
	
}

