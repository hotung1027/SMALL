package math


class Matrix{

}

/**
	* @param row_length numbers of columns equivalent to length of rows
	* @param column_length numbers of rows equivalent to length of columns
	* @param list matrix from top-left to bottom-right
	* @return a matrix with size row_length * column_length
	*
	*/
class NMatrix[T](val row_length:Int, val column_length:Int, val list:Iterable[T]) extends Matrix {
		/**
			throw a empty matrix if matrix operations failed
			@example Matrix(0,0,Nil)
			@todo support Type Vectors
		 */
	
  val rows:Iterable[Iterable[T]] =
		for {column <- 0 until column_length}
			yield list.slice(column  * row_length,  (column + 1) * row_length)
	//Please aware of cyclic reference
	val columns:Iterable[Iterable[T]] =
		for {row <- 0 until row_length}
		yield {rows.flatMap(ls => ls.slice(row, row + 1))}


	 
	
	 def *(that: NMatrix[T])(implicit zero:T=0.asInstanceOf[T], numeric:Numeric[T]) :NMatrix[T] = {
		import numeric.{plus, times}

		if (this.row_length != that.column_length) new NMatrix(0,0,Nil)
		else {
			new NMatrix(this.column_length,
					that.row_length,
					this.rows.flatMap( row => that.columns.map(column => row.zip(column).foldLeft(zero:T)({case (sum,(x,y)) => plus(sum , times(x,y))  })  ) )
					)
			
		}
	}
	def /(that:T)(implicit fractional: Fractional[T]):NMatrix[T]={
		import fractional.div
		new NMatrix(
			this.row_length,
			this.column_length,
			this.list.map(value=> div(value,that)))
		
	}
	
	def -(that:T)(implicit numeric:Numeric[T]) :NMatrix[T] = {
		import numeric.minus
		new NMatrix(
			this.row_length,
			this.column_length,
			this.list.map(value=> minus(value,that)))
	}
	def -(that:NMatrix[T])(implicit numeric:Numeric[T]) :NMatrix[T] = {
		import numeric.minus
		if (this.row_length != that.row_length && this.column_length !=that .column_length) new NMatrix(0,0,Nil)
		else new NMatrix(this.row_length,that.column_length,(this.list zip that.list)map{case (x,y) => minus(x,y)})
	}
	
	def +(that:T)(implicit numeric: Numeric[T]) :NMatrix[T] ={
		import numeric.plus
		new NMatrix[T](
			this.row_length,
			this.column_length,
			this.list.map(value => plus(value,that)))
	}
	
	def +(that:NMatrix[T])(implicit numeric: Numeric[T]) :NMatrix[T] = {
		import numeric.plus
		if (this.row_length != that.row_length && this.column_length !=that .column_length) new NMatrix(0,0,Nil)
		else new NMatrix(this.row_length,that.column_length,(this.list zip that.list)map{case (x,y) => plus(x,y)})
	}

}



class SquareMatrix[T](length:Int,list:Iterable[T]) extends NMatrix(length,length,list) {

	def cofactor(row:Int,column:Int):SquareMatrix[T] =
		new SquareMatrix(
			length-1,
			{for{index<-0 until length
			     if index != column}
				yield {
					rows
					.slice(index,index+1)
					.flatMap(col=>col.take(row)++col.takeRight(length-row-1)) }}
				.flatten )
	
	def determinant(implicit numeric: Numeric[Double]):Double= {
		import numeric.times
		length match {
			case _ if length == 1 => list.head.toString.toDouble
			case _ => rows.head.zipWithIndex.map{case (row,index) => if (index % 2 != 0)
					times(times((-1).asInstanceOf[Double], row.toString.toDouble), cofactor(index, 0).determinant)
				else times(row.toString.toDouble, cofactor(index, 0).determinant)}
				.sum
		}
	}
	def cojoint(implicit numeric: Numeric[Double]):SquareMatrix[Double]={
	  	import numeric.times
		new SquareMatrix(
			length,
		for{
			row<-0 until length
			column<-0 until length
		}
			yield times( (if((row+column) % 2 !=0) -1 else 1 ).asInstanceOf[Double], cofactor(row,column).determinant)
		)
	}
	
	def transpose:SquareMatrix[T] ={
		new SquareMatrix[T](length,columns.flatten)
	}
	
	 def /(that:T)(implicit fractional: Fractional[Double]):SquareMatrix[Double]={
		import fractional.div

		new SquareMatrix(
			length,
			this.list.map(value=> div(value.asInstanceOf[scala.Double],that.asInstanceOf[scala.Double])))
		
	}
	
	def inverse(implicit numeric: Numeric[T],fractional: Fractional[Double]):SquareMatrix[Double] = cojoint.transpose / determinant
	
	
}
