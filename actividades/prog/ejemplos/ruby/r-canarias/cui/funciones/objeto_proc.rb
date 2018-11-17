
quux = proc {
    print "QUUXQUUXQUUX!!!\n"
}

print quux
print "\n"

quux.call
print "\n"

def run ( p )
    print "Vamos a llamar a un procedimiento ... \n"
    p.call
    print "Finalizado. \n"
end

run(quux)

