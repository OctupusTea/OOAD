#include "Vector.hpp"
#include "Point.hpp"
#include "Size.hpp"
#include "Shape.hpp"
#include "Circle.hpp"

#include <algorithm>
#include <iostream>

using namespace std;

Circle::Circle( const Point &_center, const double _radius ) : Shape( Point( _center.X( ) - _radius, _center.Y( ) - _radius, _center.Z( ) ), Size( _radius * 2.0, _radius * 2.0 ) ) { };

void Circle::Render( void )
{
	cout << "Render circle in range ( " << corner.X( ) << " , " << corner.Y( ) << " ), ( " << corner.X( ) + size.X( ) << " , " << corner.Y( ) + size.Y( ) << " ) , z-order = " << corner.Z( ) << ".\n";
}

pair< Point, Size > Circle::CenterRaduis_To_CornerSize( const Point &_center, const double _radius )
{
	return pair< Point, Size >( Point( _center.X( ) - _radius, _center.Y( ) - _radius ), Size( _radius * 2.0, _radius * 2.0 ) );
}
