#include "Vector.hpp"
#include "Point.hpp"
#include "Size.hpp"
#include "Shape.hpp"
#include "Triangle.hpp"

#include <algorithm>
#include <iostream>
#include <cmath>

using namespace std;

Triangle::Triangle( const Point &_center, const double _length )
{
	double top = _center.Y( ) - _length / sqrt( 3.0 );
	double height = _length * sqrt( 3.0 ) / 2.0;
	double left = _center.X( ) - _length / 2.0;
	
	corner.X( left );
	corner.Y( top );
	corner.Z( _center.Z( ) );
	size.X( _length );
	size.Y( height );
}

void Triangle::Render( void )
{
	cout << "Render triangle in range ( " << corner.X( ) << " , " << corner.Y( ) << " ), ( " << corner.X( ) + size.X( ) << " , " << corner.Y( ) + size.Y( ) << " ) , z-order = " << corner.Z( ) << ".\n";
}

pair< Point, Size > Triangle::CenterLength_To_CornerSize( const Point &_center, const double _length )
{
	double top = _center.Y( ) - _length / sqrt( 3.0 );
	double height = _length * sqrt( 3.0 ) / 2.0;
	double left = _center.X( ) - _length / 2.0;
	
	return pair< Point, Size >( Point( left, top ), Size( _length, height ) );
}
