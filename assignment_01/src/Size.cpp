#include "Vector.hpp"
#include "Size.hpp"

#include <algorithm>
#include <cmath>

using namespace std;

Size::Size( void )
{
	z_stat = Z_DISABLED;
}

Size::Size( const double _x, const double _y ) : Vector( _x, _y )
{
	z_stat = Z_DISABLED;
}

Size::Size( const pair< double, double > _x_y ) : Vector( _x_y )
{
	z_stat = Z_DISABLED;
}

double Size::Z( void ) const
{
	return NAN;
}

bool Size::Z( const double _z )
{
	return false;
}

bool Size::operator==( const Size &that )
{
	return ( x == that.x and y == that.y );
}
