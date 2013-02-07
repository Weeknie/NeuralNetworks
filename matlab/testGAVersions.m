% Reload the 
reloadJar;
settings.set('runToTarget', true);
settings.set('solutionTarget', 100);
settings.set('block', true);
settings.set('threadCount', 5);
settings.set('verbose', false);

popSizes = 8:8:56;
GAVersions = {'mkI', 'mkII', 'mkIII'};
settings.set('GAmkIII.popDivision', 4);

output = zeros(size(GAVersions, 2), size(popSizes, 2), 3);

maxLength = 0;
for i = 1:size(GAVersions, 2)
    len = length(GAVersions{i});
    if(len > maxLength)
        maxLength = len;
    end
end

for j = 1:size(GAVersions, 2)
    settings.set('GAVersion', GAVersions{j});
    for i = 1:size(popSizes, 2)
        
        disp(strcat(['GA Version: ', GAVersions{j}, repmat(' ', 1, maxLength + 2 - length(GAVersions{j})), 'popSize: ', num2str(popSizes(i))]));
        
        settings.set('circleMoveToXYDirection.popSize', popSizes(i));
        api.run(settings);
        output(j, i, :) = api.poll()';
        pause(0.1);
    end
end

plotData;