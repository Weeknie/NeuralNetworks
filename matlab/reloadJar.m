% Reloads the Neural Networks jar and reinstantiates all API classes

% Clear all variables, to release any instances from java objects in the jar
clear

% Reinclude the jar to include new updates
javarmpath D:\Documents\NetBeansProjects\NeuralNetworks\dist\NeuralNetworks.jar
javaaddpath D:\Documents\NetBeansProjects\NeuralNetworks\dist\NeuralNetworks.jar

% Instantiate the java classes
main = neuralNets.main.Main;
api = neuralNets.matlab.Matlab;
settings = neuralNets.matlab.Settings;