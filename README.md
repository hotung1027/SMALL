# Scala-MAT *scala written MAT*
experimental,constructing,welcome to contribute

# NMatrix *NumericMatrix*
a matrix defined by `number of columns` * `number of rows` and `Iterable[T]`
- **current only support for Numeric types**
- support basic numeric operation ` - + * / `

## Example
### A 4 * 3 Matrix defined as:
#### `number of columns = 4` * `number of rows =3` and 
#### `Iterable(1,2,3,4,5,6,7,8,9,10,11,12):Iterable[Int]`
      1   2   3   4
      _   _   _   _
    1|1   2   3   4
    2|5   6   7   8
    3|9   10  11  12

the Iterable start from ***Top-Left*** to ***Bottom-Right*** 
## A Matrix is also follow by these ruled values
#### Rows 
    Iterable(
    Iterable(1,2,3,4),
    Iterable(5,6,7,8),
    Iterable(9,10,11,12))
    
#### Columns
    Iterable(
    Iterable(1,5,9),
    Iterable(2,6,10),
    Iterable(3,7,11),
    Iterable(4,8,12))
    
# SquareMatrix *Perfect Sqaured Matrix*
#### supported operation:

- transpose
- determinant
- inverse

## Example 
#### A 4 * 4 matrix

      1   2   3   4
      _   _   _   _
    1|1   2   3   4
    2|5   6   7   8
    3|9   10  11  12
    4|13  14  15  16
    
#### A 3 * 3 matrix
      1   2   3 
      _   _   _
    1|1   2   3
    2|5   6   7 
    3|9   10  11

