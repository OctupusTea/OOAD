#ifndef CIRCLE
#define CIRCLE

#include "Vector.hpp"
#include "Point.hpp"
#include "Size.hpp"
#include "Shape.hpp"

#include <algorithm>
#include <iostream>

using namespace std;

class Circle : public Shape
{
	public:
		Circle( const Point &_center, const double _radius );

		void Render( void );

		static pair< Point, Size > CenterRaduis_To_CornerSize( const Point &_center, const double _radius );
};

#endif
