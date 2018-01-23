package math.matrices

import org.scalatest.FunSuite

trait MatrixTest extends FunSuite{
	test("Number Matrix"){
		val matrix1 = new NumberMatrix(2,3,Array[Double](1,2,3,4,5,6))
		val matrix2 = new NumberMatrix(2,3,Array[Double](1,2,3,4,5,6).map(_-1))
		val matrix3 = new NumberMatrix(3,2,Array[Double](1,2,3,4,5,6).map(_-1))
		assert(matrix1 - 1 equal matrix2, "scala operation")
		assert(matrix1 - matrix2 equal new NumberMatrix(2, 3, Array[Double](1, 1, 1, 1, 1, 1)), "matrix to matrix operation")
		val m1m2 = intercept[NoSuchMethodException] {matrix1 * matrix2 }
		assert(m1m2.isInstanceOf[NoSuchMethodException] ,"non-operable matrix multiplication" )
		assert(matrix1 * matrix3 equal new NumberMatrix(2, 2, Array[Double](16.0, 22.0, 34.0, 49.0)), "operable matrix multiplication")
	}
	test("Square Matrix"){
		val matrix1 = new SquareMatrix(3,Array[Double](1,2,3,4,5,6,7,8,9))
		val matrix2 = intercept[IllegalArgumentException]{new SquareMatrix(3,Array[Double](1,2,3,4,5,6).map(_-1))}
		val matrix3 = new SquareMatrix(3,Array[Double](1,2,3,4,5,6,7,8,9).map(_-1))
		val matrix4 = new SquareMatrix(3,Array[Double](2,3,5,7,11,13,17,19,23))
		assert(matrix2 .isInstanceOf[IllegalArgumentException] ,"illegal size construction of square matrix")
		assert(matrix1 * matrix3 equal new SquareMatrix(3, Array[Double](24.0, 30.0, 36.0, 51.0, 66.0, 81.0, 78.0, 102.0, 126.0)), "matrix matrix multiplication")
		val m1inverse = intercept[NoSuchMethodException] {matrix1.inverse }
		assert(m1inverse.isInstanceOf[NoSuchMethodException] ,"inverse a matrix that determinant == 0" )
		assert(matrix4.inverse equal new SquareMatrix(3,
			Array(-0.07692307692307693, -0.3333333333333333, 0.20512820512820512,
			-0.7692307692307693, 0.5, -0.11538461538461539,
			0.6923076923076923, -0.16666666666666666, -0.01282051282051282)), "matrix inverse")
		assert(matrix4.cofactor(1, 2) equal new SquareMatrix(2, Array(2, 3, 17, 19)), "matrix cofact with available index")
		assert(matrix4.cojoint equal new SquareMatrix(3, Array(6, 60, -54, 26, -39, 13, -16, 9, 1)))
	}
	
}
