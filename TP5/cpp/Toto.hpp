#ifndef TOTO_HPP
#define TOTO_HPP

#include <string>
#include "IToto.hpp"
#include "Mom.hpp"

class Toto : public Mom, public IToto {
	private:
		std::string stringPrivate;
		Toto(std::string);
		std::string getStringPrivate();
		void setStringPrivate(std::string);
	protected:
		int intProtected;
		Toto(int);
		int getIntProtected();
		void setIntProtected(int);
	public:
		double doublePublic;
		Toto(double);
		double getDoublePublic();
		void setDoublePublic(double);
}; // Toto

// private implementation
Toto::Toto(std::string) {}
std::string Toto::getStringPrivate() { return std::string(); }
void Toto::setStringPrivate(std::string) {}

// protected implementation
Toto::Toto(int) {}
int Toto::getIntProtected() { return int(); }
void Toto::setIntProtected(int) {}

// public implementation
Toto::Toto(double) {}
double Toto::getDoublePublic() { return double(); }
void Toto::setDoublePublic(double) {}

#endif //TOTO_HPP
