package br.com.conrado.fcontrol.integration.inmemory.repository.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import br.com.conrado.fcontrol.integration.IntegrationProfiles;
import br.com.conrado.fcontrol.integration.repository.UserRepository;

@Repository
@Profile(IntegrationProfiles.INMEMORY)
public class UserInMemoryRepositoryImpl implements UserRepository {

}
