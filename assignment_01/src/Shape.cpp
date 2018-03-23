#include "Vector.hpp"
#include "Point.hpp"
#include "Size.hpp"
#include "Shape.hpp"

#include <algorithm>
#include <iostream>

using namespace std;

Shape::Shape( void ) : corner( ), size( ) { };

Shape::Shape( const Point &_corner, const Size &_size ) : corner( _corner ), size( _size ) { };

const Point& Shape::Corner( void )
{
	return corner;
}

const Size& Shape::Shape_Size( void )
{
	return size;
}

void Shape::Render( void )
{
	cout << "Render shape in range ( " << corner.X( ) << " , " << corner.Y( ) << " ), ( " << corner.X( ) + size.X( ) << " , " << corner.Y( ) + size.Y( ) << " ) , z-order = " << corner.Z( ) << ".\n";
}

bool Shape::operator<( const Shape &that ) const
{
	return corner < that.corner;
}

bool Shape::Compare( const Shape *a, const Shape *b )
{
	return a -> corner < b -> corner;
}
