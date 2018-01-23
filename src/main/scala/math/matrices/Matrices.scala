package math.matrices

import scala.reflect.{classTag, ClassTag}
/**
	* @param colNum numbers of columns equivalent to length of rows
	* @param rowNum numbers of rows equivalent to length of columns
	* @param array matrix from top-left to bottom-right
	* @return a matrix with size row_length * column_length
	*
	*/
class NumberMatrix(override val rowNum:Int,override val colNum:Int,override val array:Array[Double]) extends Matrice[Double](rowNum ,colNum ,array ) {
	

	
	require(array.length == rowNum * colNum, "The numbers given doesn't match the " +
		s"size of the matrix! size of numbers: ${array.length}, number of rows * number of columns: ${rowNum * colNum}")
	/**
	throw exception if  size of the matrix unmatched rowNum * col Num
			@todo support Type Vectors
		*/
	
	def equalSize(that: NumberMatrix): Boolean = this.colNum == that.colNum && this.rowNum == that.rowNum
	
	def equalValues(that: NumberMatrix): Boolean = {
		this.array zip that.array
	}.foldLeft(true) { case (bool, (l, r)) => bool && l == r }
	
	def equal(that: NumberMatrix): Boolean = equalSize(that) && equalValues(that)
	
	override val rows:Array[Vector[Double]] =
		{for {row <- 0 until rowNum}
			yield {
				array.slice(row * colNum, (row + 1) * colNum)
			}
				.toVector
		}.toArray
	
	override val cols: Array[Vector[Double]] = {
		for {col <- 0 until colNum}
			yield {
				rows.map(ls => ls(col))
			}
				.toVector
	}.toArray
	
	/**
		* Helper method for construct
		*/
	def constructWith(iter:Iterable[Double])(implicit rowNum:Int = this.rowNum,
	                                         colNum:Int = this.colNum,ctag:ClassTag[Double]):NumberMatrix =
		new NumberMatrix(rowNum,colNum,iter.toArray)
	
	
	/**
		* Check Both Matrix able to do multiplication
		*/
	def operable(that: NumberMatrix): Boolean = this.colNum == that.rowNum
	
	def interact(that: NumberMatrix, f: (Double, Double) => Double, concate: (Double, Double) => Double)(implicit zero: Double): Iterable[Double] =
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
	def coOps(that: NumberMatrix, f: (Double, Double) => Double): Iterable[Double] =
		if (equalSize(that))
			{this.array zip that.array}
				.map { case (l, r) => f(l, r) }
		else
			throw new NoSuchMethodException(s"The Size of two matrix are not equal " +
				s"(${this.array.length} != ${that.array.length})")
	
	
	override def scalaOps(that: Double, f: (Double, Double) => Double): Iterable[Double] =		array.map(value => f(value, that))
	//		array.map(value => f(value, that))
	/**
		* Matrix to Matrix Operation
		* */
	def +(that:NumberMatrix) :NumberMatrix = constructWith(coOps(that,plus))(rowNum,colNum,classTag[Double])
	
	def -(that:NumberMatrix) :NumberMatrix = constructWith(coOps(that,minus))(rowNum,colNum,classTag[Double])
	
	def *(that: NumberMatrix) :NumberMatrix = constructWith(interact(that,times,plus)(zero))(this.rowNum,that.colNum,classTag[Double])
	
	def /(that: NumberMatrix) :NumberMatrix = constructWith(interact(that,divide,plus)(zero))(this.rowNum,that.colNum,classTag[Double])
	
	/**
		* Scalar Matrix Operation
		* */
	
	def +(that:Double):NumberMatrix = constructWith(scalaOps(that,plus))(rowNum,colNum,classTag[Double])
	
	def -(that:Double):NumberMatrix = constructWith(scalaOps(that,minus))(rowNum,colNum,classTag[Double])
	
	def *(that:Double):NumberMatrix = constructWith(scalaOps(that,times))(rowNum,colNum,classTag[Double])
	
	def /(that:Double):NumberMatrix = constructWith(scalaOps(that,divide))(rowNum,colNum,classTag[Double])
	
	
	def plus: (Double, Double) => Double = _ + _

	def minus: (Double, Double) => Double = _ - _
	
	def times: (Double, Double) => Double =	_ * _
	
	def divide: (Double, Double) => Double = _ / _
	
	def zero:Double = 0.0

}



class SquareMatrix(val length:Int,override val array:Array[Double]) extends NumberMatrix(length,length,array) {
	require(array.length == length * length, "The numbers given doesn't match the " +
		s"size of the matrix! size of numbers: ${array.length}, number of rows * number of columns: ${length * length}")
	
	def cofactor(row:Int,column:Int):SquareMatrix =
		if (row * length + column >=0
			&& row * length + column <= length * length)
			new SquareMatrix(
				length-1,
				{for{index<-0 until length
				     if index != row}
					yield {
						rows
							.slice(index,index+1)
							.flatMap(col=>col.take(column)++col.takeRight(length-column-1)) }}
					.flatten.toArray )
		else throw new NoSuchElementException(s"Index out of Boundary,index:${row*length+column}, size:$length")
	
	def determinant:Double= { length match {
		case _ if length == 1 => array.headOption.getOrElse(throw new NoSuchElementException)
		case _ =>
			rows.headOption.getOrElse(throw new NoSuchElementException).zipWithIndex
				.map{case (row,index) =>
					{if (index % 2 != 0) -1.0 else 1} *	row * cofactor(0, index).determinant }
				.sum
	}
	}
	def cojoint:SquareMatrix={
		
		new SquareMatrix(
			length,{
				for{
					row<-0 until length
					column<-0 until length
				}
					yield {{if((row+column) % 2 !=0) -1.0 else 1.0} * cofactor(row,column).determinant}}.toArray
		)
	}
	
	def transpose: SquareMatrix = this.constructWith(cols.flatten.toArray)
	
	override def constructWith(iter: Iterable[Double])(implicit rowNum: Int, colNum: Int, ctag: ClassTag[Double]): SquareMatrix = new SquareMatrix(length,iter.toArray)
	
	override def scalaOps(that: Double, f: (Double, Double) => Double): Iterable[Double] =super.scalaOps(that,f).toArray
	
	override def +(that:Double): SquareMatrix= this.constructWith(scalaOps(that,plus))
	
	override def -(that:Double) :SquareMatrix  = this.constructWith(scalaOps(that,minus))
	
	override def *(that:Double):SquareMatrix = this.constructWith(scalaOps(that,times))
	
	override def /(that:Double):SquareMatrix = this.constructWith(scalaOps(that,divide))
	
	def inverse: SquareMatrix =
		if (determinant != 0)
			cojoint.transpose / determinant
		else
			throw new NoSuchMethodException(s"The determinant is $determinant, the matrix is not invertible")
	
	override def plus: (Double, Double) => Double =  _ + _
	override def minus: (Double, Double) => Double =  _ - _
	override def times: (Double, Double) => Double = _ * _
	override def divide: (Double, Double) => Double =  _ / _
	override def zero:Double = 0.0
	
	
	
	
}
