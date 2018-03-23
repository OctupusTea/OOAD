#include "Vector.hpp"
#include "Point.hpp"
#include "Size.hpp"
#include "Shape.hpp"
#include "Square.hpp"

#include <algorithm>
#include <iostream>

using namespace std;

Square::Square( const Point &_center, const double _length ) : Shape( Point( _center.X( ) - _length / 2.0, _center.Y( ) - _length / 2.0, _center.Z( ) ), Size( _length, _length ) ) { };

void Square::Render( void )
{
	cout << "Render square in range ( " << corner.X( ) << " , " << corner.Y( ) << " ), ( " << corner.X( ) + size.X( ) << " , " << corner.Y( ) + size.Y( ) << " ) , z-order = " << corner.Z( ) << ".\n";
	
}

pair< Point, Size > Square::CenterLength_To_CornerSize( const Point &_center, const double _length )
{
	return pair< Point, Size >( Point( _center.X( ) - _length / 2.0, _center.Y( ) - _length / 2.0 ), Size( _length, _length ) );
}
