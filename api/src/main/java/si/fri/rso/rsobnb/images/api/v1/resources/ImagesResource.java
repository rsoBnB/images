package si.fri.rso.rsobnb.images.api.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.metrics.annotation.Metered;
import si.fri.rso.rsobnb.images.Image;
import si.fri.rso.rsobnb.images.services.ImagesBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Path("/images")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Log
public class ImagesResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    private ImagesBean imagesBean;

    @GET
    @Metered
    public Response getImages() {

        List<Image> images = imagesBean.getImages(uriInfo);

        return Response.ok(images).build();
    }

    @GET
    @Path("/{imageId}")
    public Response getImage(@PathParam("imageId") String imageId) {

        Image image = imagesBean.getImage(imageId);

        if (image == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(image).build();
    }

    @POST
    public Response createImage(Image image) {

        if (image.getDescription() == null || image.getDescription().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            image = imagesBean.createImage(image);
        }

        if (image.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(image).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(image).build();
        }
    }

    @PUT
    @Path("{imageId}")
    public Response putImage(@PathParam("imageId") String imageId, Image image) {

        image = imagesBean.putImage(imageId, image);

        if (image == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            if (image.getId() != null)
                return Response.status(Response.Status.OK).entity(image).build();
            else
                return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    @Path("{imageId}")
    public Response deleteImage(@PathParam("imageId") String imageId) {

        boolean deleted = imagesBean.deleteImage(imageId);

        if (deleted) {
            return Response.status(Response.Status.GONE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
