#include "Vector.hpp"
#include "Point.hpp"

#include <algorithm>

using namespace std;

Point::Point( void )
{
	z_stat = Z_NOT_SET;
}

Point::Point( const double _x, const double _y ) : Vector( _x, _y )
{
	z_stat = Z_NOT_SET;
}

Point::Point( const double _x, const double _y, const double _z ) : Vector( _x, _y, _z )
{
	z_stat = Z_SET;
}

Point::Point( const pair< double, double > _x_y ) : Vector( _x_y )
{
	z_stat = Z_NOT_SET;
}

Point::Point( const pair< double, double > _x_y, const double _z ) : Vector( _x_y, _z )
{
	z_stat = Z_SET;
}

double Point::Z( void ) const
{
	return z;
}

bool Point::Z( const double _z )
{
	z = _z;
	return true;
}

bool Point::operator==( const Point &that ) const
{
	return ( x == that.x and y == that.y and z == that.z );
}

bool Point::operator<( const Point &that ) const
{
	return z < that.z;
}
