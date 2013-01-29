reloadJar;
settings = neuralNets.matlab.Settings;
settings.setProperty('runBatch', true);
api.start(settings);
pause(5)
api.stop();