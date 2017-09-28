package com.sollian.base.view.hugeimage.decoder;

/**
 * Interface for decoder (and region decoder) factories.
 *
 * @param <T> the class of decoder that will be produced.
 */
public interface DecoderFactory<T> {
    /**
     * Produce a new instance of a decoder with type {@link T}.
     *
     * @return a new instance of your decoder.
     */
    T make() throws Exception;
}
