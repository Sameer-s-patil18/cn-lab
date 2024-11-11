#Create a simulator object
set ns [ new Simulator ]
set tf [ open lab1.tr w ]
$ns trace-all $tf
#Open the NAM trace file
set nf [ open lab1.nam w ]
$ns namtrace-all $nf
#Create four nodes
set n0 [ $ns node ]
set n1 [ $ns node ]
set n2 [ $ns node ]
set n3 [ $ns node ]
#Define different colors for data flows (for NAM)
$ns color 1 "red"
$ns color 2 "blue"
$n0 label "source/udp0"
$n1 label "source/udp1"
$n2 label "Router"
$n3 label "Destination/Null"
#Create links between the nodes
$ns duplex-link $n0 $n2 10Mb 300ms DropTail
$ns duplex-link $n1 $n2 10Mb 300ms DropTail
$ns duplex-link $n2 $n3 1Mb 300ms DropTail
#Set Queue Size of link
$ns set queue-limit $n0 $n2 10
$ns set queue-limit $n1 $n2 10
$ns set queue-limit $n2 $n3 5
set udp0 [ new Agent/UDP ]
$ns attach-agent $n0 $udp0
#Setup a CBR over UDP connection
set cbr0 [ new Application/Traffic/CBR ]
$cbr0 attach-agent $udp0
set null3 [ new Agent/Null ]
$ns attach-agent $n3 $null3
set udp1 [ new Agent/UDP ]
$ns attach-agent $n1 $udp1
set cbr1 [ new Application/Traffic/CBR ]
$cbr1 attach-agent $udp1
$udp0 set class_ 1
$udp1 set class_ 2
#Setup a UDP connection
$ns connect $udp0 $null3
$ns connect $udp1 $null3
$cbr1 set packetSize_ 500Mb
$cbr1 set interval_ 0.005
#Define a 'finish' procedure
proc finish {} {
global ns nf tf
$ns flush-trace
exec nam lab1.nam &
exec echo "The number of packet drop is:" &
exec grep -c "^d" lab1.tr &
close $tf
close $nf
exit 0
}
#Schedule events for the CBR agents
$ns at 0.1 "$cbr0 start"
$ns at 0.1 "$cbr1 start"
#Call the finish procedure after 10 seconds of simulation time
$ns at 10.0 "finish"
#Run the simulation
$ns run
