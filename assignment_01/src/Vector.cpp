#include "Vector.hpp"

#include <algorithm>

using namespace std;

Vector::Vector( void ) : x( 0.0 ), y( 0.0 ), z( 0.0 ), z_stat( Z_UNDETERMINED ) { };

Vector::Vector( const double _x, const double _y, const double _z ) : x( _x ), y( _y ), z( _z ), z_stat( Z_UNDETERMINED ) { };

Vector::Vector( const pair< double, double > _x_y, const double _z ) : x( _x_y.first ), y(  _x_y.second ), z( _z ), z_stat( Z_UNDETERMINED ) { };

//	input value in the setter tempararily not restricted.
double Vector::X( void ) const
{
	return x;
}

bool Vector::X( const double _x )
{
	x = _x;
	return true;
}

double Vector::Y( void ) const
{
	return y;
}

bool Vector::Y( const double _y )
{
	y = _y;
	return true;
}

pair< double, double > Vector::X_Y( void ) const
{
	return pair< double, double >( x, y );
}

bool Vector::X_Y( const double _x, const double _y )
{
	x = _x;
	y = _y;
	return true;
}

bool Vector::X_Y( const pair< double, double > _x_y )
{
	x = _x_y.first;
	y = _x_y.second;

	return true;
}
