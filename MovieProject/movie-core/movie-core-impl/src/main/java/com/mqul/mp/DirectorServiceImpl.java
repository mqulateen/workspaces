package com.mqul.mp;

import com.mqul.mp.repository.DirectorRepo;
import com.mqul.mp.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@Service
public class DirectorServiceImpl implements DirectorService
{
    @Autowired
    private DirectorRepo directorRepo;

    @Override
    public DirectorDTO createDirector(String firstName, String lastName, String imdbRef)
    {
        if(Objects.nonNull(getDirectorByImdbRef(imdbRef)))
        {
            final String errorMessage = String.format("Director with imdbRef [%s] already exists", imdbRef);
            throw new IllegalArgumentException(errorMessage);
        }

        final Director director = new Director(firstName, lastName, imdbRef);
        directorRepo.createDirector(director);

        final DirectorDTO directorDTO = directorRepo.findDirectorById(director.getID()).transferToDTO();

        return directorDTO;
    }

    @Override
    public DirectorDTO getDirectorById(int id)
    {
        return directorRepo.findDirectorById(id).transferToDTO();
    }

    @Override
    public DirectorDTO getDirectorByImdbRef(String imdbRef)
    {
        final Director director = directorRepo.findDirectorByRef(imdbRef);
        return (director != null) ? director.transferToDTO() : null;
    }

    @Override
    public List<DirectorDTO> getDirectors()
    {
        final List<Director> directors = directorRepo.getAllDirectors();

        final List<DirectorDTO> directorDTOS = new ArrayList<>(directors.size());
        for(Director director : directors)
        {
            directorDTOS.add(director.transferToDTO());
        }

        return directorDTOS;
    }

    @Override
    public DirectorDTO updateDirector(int id, String firstName, String lastName, String imdbRef)
    {
        if(isNull(firstName) && isNull(lastName) && isNull(imdbRef))
            throw new IllegalArgumentException("Atleast one updatable field must be present");

        return directorRepo.updateDirector(id, firstName, lastName, imdbRef).transferToDTO();
    }

    @Override
    public void deleteDirector(int id)
    {
        directorRepo.deleteDirector(id);
    }
}
