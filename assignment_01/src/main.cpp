#include "Vector.hpp"
#include "Point.hpp"
#include "Size.hpp"
#include "Shape.hpp"
#include "Circle.hpp"
#include "Square.hpp"
#include "Triangle.hpp"
#include "ShapeGen.hpp"

#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int main( int argc, char **argv )
{
	ShapeGen shape_generator = ShapeGen( );
	shape_generator.Randomize( );
	vector< Shape* > shape_list = shape_generator.Generate( );
	
	sort( shape_list.begin( ), shape_list.end( ), Shape::Compare );
	
	cout << "Shape count = " << shape_list.size( ) << endl;

	for( auto *i : shape_list )
	{
		i -> Render( );
	}

	return 0;
}
