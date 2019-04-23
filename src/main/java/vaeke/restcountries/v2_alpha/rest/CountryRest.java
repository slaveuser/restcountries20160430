/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package vaeke.restcountries.v2_alpha.rest;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import vaeke.restcountries.domain.ResponseEntity;
import vaeke.restcountries.v2_alpha.domain.Country;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.List;

@Provider
@Path("rest/v2-alpha")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class CountryRest {

    private static final Logger LOG = Logger.getLogger(CountryRest.class);

    @GET
    @Path("all")
    public Object getAllCountries() {
        return this.getCountries();
    }

    @GET
    public Object getCountries() {
        LOG.info("Getting all");
        return CountryService.getInstance().getAll();
    }

    @GET
    @Path("alpha/{alphacode}")
    public Object getByAlpha(@PathParam("alphacode") String alpha) {
        LOG.info("Getting by alpha " + alpha);
        Country country = CountryService.getInstance().getByAlpha(alpha);
        if (country != null) {
            return country;
        }
        return getResponse(Response.Status.NOT_FOUND);
    }

    @GET
    @Path("alpha/")
    public Object getByAlphaList(@QueryParam("codes") String codes) {
        LOG.info("Getting by list " + codes);
        try {
            List<Country> countries = CountryService.getInstance().getByCodeList(codes);
            if (!countries.isEmpty()) {
                return countries;
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("currency/{currency}")
    public Object getByCurrency(@PathParam("currency") String currency) {
        LOG.info("Getting by currency " + currency);
        try {
            List<Country> countries = CountryService.getInstance().getByCurrency(currency);
            if (!countries.isEmpty()) {
                return countries;
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("name/{name}")
    public Object getByName(@PathParam("name") String name, @QueryParam("fullText") boolean fullText) {
        LOG.info("Getting by name " + name);
        try {
            List<Country> countries = CountryService.getInstance().getByName(name, fullText);
            if (!countries.isEmpty()) {
                return countries;
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("callingcode/{callingcode}")
    public Object getByCallingCode(@PathParam("callingcode") String callingcode) {
        LOG.info("Getting by calling code " + callingcode);
        try {
            List<Country> countries = CountryService.getInstance().getByCallingCode(callingcode);
            if (!countries.isEmpty()) {
                return countries;
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("capital/{capital}")
    public Object getByCapital(@PathParam("capital") String capital) {
        LOG.info("Getting by capital " + capital);
        try {
            List<Country> countries = CountryService.getInstance().getByCapital(capital);
            if (!countries.isEmpty()) {
                return countries;
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("region/{region}")
    public Object getByRegion(@PathParam("region") String region) {
        LOG.info("Getting by region " + region);
        try {
            List<Country> countries = CountryService.getInstance().getByRegion(region);
            if (!countries.isEmpty()) {
                return countries;
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("lang/{lang}")
    public Object getByLanguage(@PathParam("lang") String language) {
        LOG.info("Getting by language " + language);
        try {
            List<Country> countries = CountryService.getInstance().getByLanguage(language);
            if (!countries.isEmpty()) {
                return countries;
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    public Object doPOST() {
        LOG.info("Handling POST Request");
        return getResponse(Response.Status.METHOD_NOT_ALLOWED);
    }

    private Response getResponse(Response.Status status) {
        Gson gson = new Gson();
        return Response
                .status(status)
                .entity(gson.toJson(new ResponseEntity(status.getStatusCode(),
                        status.getReasonPhrase()))).build();
    }
}
